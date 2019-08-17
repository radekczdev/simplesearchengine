## Simple Search Engine

Application provides indexing of documents (one document is a String). Indexing is using TF-IDF algoritm for sorting the output.
The output contains documents containing the index, sorted by frequency of word occurrence.

App was written in Java8 and runs in Console. Build of the app is provided by Maven. Provided tests are written using Junit4 and Mockito frameworks, JSon files are 

**.json** input file should be placed in _resources_ folder. One json element contains **body** field with the String.

### Use of application: 
- mvn clean install >  mvn exec:java -Dexec.mainClass="com.czajor.simplesearchengine.SimpleSearchEngine"
- start the app using Maven (
- provide the json file name (file which exists: documents_lines.json)
- provide index (word) to be searched
- if the index exists, app will show the documents with calculated value of TF-IDF
- decide if you want to use it further by saying 'no' for stop or any string for continue

