## Simple Search Engine

Application provides indexing of documents (one document is a String). Indexing is using TF-IDF algoritm for sorting the output.
The output contains documents containing the index, sorted by frequency of word occurrence.

It was written in Java8. Build of the app is provided by Maven. 

**.json** input file should be placed in _resources_ folder. One json element contains **body** field with the String.

###Use of application: 
- start the app using Maven
- provide the json file name
- provide index (word) to be searched
- if the index exists, app will show the documents with calculated value of TF-IDF
- decide if you want to use it further by saying 'no' for stop or any string for continue

