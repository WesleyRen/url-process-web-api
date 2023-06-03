package my.raptive.url.processor.process;

import my.raptive.url.common.repository.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class WebContentProcessService {

    @Autowired
    ProcessInfoRepository processInfoRepository;
    @Autowired
    UrlRepository urlRepository;

    @Async
    public void process(int shardId) {
        // Get the urls from the database.
        List<Url> urlList = urlRepository.findByStatusAndShardId(shardId);
        System.out.printf("Processing %d urls %s.\n",  urlList.size(), urlList.stream().map(Url::getUrl).reduce("", (a, b) -> a + ", " + b));
        // Process the urls.
        for (Url url : urlList) {
            // Get the web content.
            Document webContent = null;
            String errorMessage = null;
            ProcessInfo processInfo = processInfoRepository.findByUrlId(url.getId());
            if (processInfo == null) {
                processInfo = new ProcessInfo();
                processInfo.setUrlId(url.getId());
            }
            try {
                webContent = scrapeWebPage(url.getUrl());
            } catch (IOException e) {
                errorMessage = e.getMessage();
            }
            // Parse the web content.
            updateProcessInfo(url, processInfo, webContent, errorMessage);
            urlRepository.save(url);
            processInfoRepository.save(processInfo);
        }
    }

    private void updateProcessInfo(Url url, ProcessInfo processInfo, Document webContent, String errorMessage) {
        if (errorMessage != null) {
            processInfo.setErrorMessage(errorMessage);
            url.setProcessStatus(ProcessStatus.FAILED);
            return;
        }
        String title = webContent.title();
        String description = title; // Default to title if no description is found.

        Elements descTags = webContent.select("meta[name=description]");
        if (descTags.size() > 0) {
            description = descTags.get(0).attr("content");
            description = description.substring(0, Math.min(description.length(), 4000));
        }
        String body = webContent.select("body").text();
        body = body.substring(0, Math.min(body.length(),4000));
        processInfo.setTitle(title).setDescription(description).setBody(body);
        url.setProcessStatus(ProcessStatus.SUCCESS);
    }

    private Document scrapeWebPage(String url) throws IOException{
        Document doc = Jsoup.connect(url).get();
        System.out.println("title is: " + doc.title());
        return doc;
    }

}
