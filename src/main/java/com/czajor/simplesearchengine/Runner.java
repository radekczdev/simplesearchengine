package com.czajor.simplesearchengine;

import com.czajor.simplesearchengine.domain.Index;
import com.czajor.simplesearchengine.repository.DocumentRepository;
import com.czajor.simplesearchengine.repository.IndexRepository;
import com.czajor.simplesearchengine.service.IndexingService;
import com.czajor.simplesearchengine.service.InitiateDatabaseService;
import com.czajor.simplesearchengine.service.PrintToConsoleService;
import com.czajor.simplesearchengine.service.SearchIndexService;

import java.util.Map;
import java.util.Scanner;

public class Runner {
    private IndexRepository indexRepository;
    private DocumentRepository documentRepository;
    private InitiateDatabaseService databaseService = new InitiateDatabaseService();
    private IndexingService indexingService = new IndexingService();
    private SearchIndexService searchIndexService;

    public Runner(IndexRepository indexRepository, DocumentRepository documentRepository) {
        this.indexRepository = indexRepository;
        this.documentRepository = documentRepository;
        this.searchIndexService = new SearchIndexService(indexRepository, documentRepository);

    }

    public void run() throws Exception {
        PrintToConsoleService.printWelcome();
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        databaseService.addToDatabase(path, documentRepository);
        indexingService.run(documentRepository, indexRepository);
        do {
            PrintToConsoleService.printProvideIndex();
            String index = in.nextLine();
            sort(searchIndexService, index);
            PrintToConsoleService.printEnd();
        } while(!in.nextLine().equals("yes"));
    }

    private void sort(SearchIndexService searchIndexService, String index) {
        try {
            Map<Long, Double> tfIdfMap = searchIndexService.sortByTfIdf(index);
            Index curIndex = indexRepository.findIndexById(index).get();
            curIndex.getTfIdfMap().putAll(tfIdfMap);
            PrintToConsoleService.searchResult(curIndex);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
