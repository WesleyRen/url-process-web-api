package my.raptive.url.processor.process;

import my.raptive.url.processor.ProcessStatus;
import my.raptive.url.processor.repository.UrlProcessInfo;
import my.raptive.url.processor.repository.UrlProcessRepository;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class WebContentProcessService {
    UrlProcessRepository urlProcessRepository;

    @Autowired
    WebContentProcessService(UrlProcessRepository urlProcessRepository) {
        this.urlProcessRepository = urlProcessRepository;
    }

    public void process(String processId) {
        // Get the urls from the database.
        List<UrlProcessInfo> urlProcessInfoList = urlProcessRepository.findByProcessId(processId);
        // Process the urls.
        for (UrlProcessInfo urlProcessInfo : urlProcessInfoList) {
            // Get the web content.
            Document webContent = null;
            String errorMessage = null;
            try {
                webContent = scrapeWebPage(urlProcessInfo.getUrl());
            } catch (IOException e) {
                errorMessage = e.getMessage();
            }
            // Parse the web content.
            updateProcessInfo(urlProcessInfo, webContent, errorMessage);
        }
        // Store the results in the database.
        urlProcessRepository.saveAll(urlProcessInfoList);
    }

    private void updateProcessInfo(UrlProcessInfo urlProcessInfo, Document webContent, String errorMessage) {
        if (errorMessage != null) {
            urlProcessInfo.setErrorMessage(errorMessage).setProcessStatus(ProcessStatus.FAILED);
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
        urlProcessInfo.setTitle(title).setDescription(description).setBody(body).setProcessStatus(ProcessStatus.SUCCESS);
    }

    private Document scrapeWebPage(String url) throws IOException{
        Document doc = Jsoup.connect(url).get();
        System.out.println("title is: " + doc.title());
        return doc;
    }

}
