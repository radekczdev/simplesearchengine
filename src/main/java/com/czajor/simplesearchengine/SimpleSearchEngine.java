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

public class SimpleSearchEngine {
    private static final IndexRepository indexRepository = IndexRepository.getInstance();
    private static final DocumentRepository documentRepository = DocumentRepository.getInstance();

    public static void main(String[] args) {
        InitiateDatabaseService databaseService = new InitiateDatabaseService();
        IndexingService indexingService = new IndexingService();
        SearchIndexService searchIndexService = new SearchIndexService(indexRepository, documentRepository);
        PrintToConsoleService.printWelcome();
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        do {
            databaseService.addToDatabase(path, documentRepository);
            indexingService.run(documentRepository, indexRepository);
            PrintToConsoleService.printProvideIndex();
            String index = in.nextLine();
            searchIndexService.sortByTfIdf(index);

            Map<Long, Double> tfIdfMap = searchIndexService.sortByTfIdf(index);

            Index curIndex = indexRepository.findIndexById(index).get();
            curIndex.getTfIdfMap().putAll(tfIdfMap);
            PrintToConsoleService.searchResult(curIndex);
        } while(!in.next().equals("*"));
    }
}
