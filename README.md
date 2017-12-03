# <i>SentimentAnalysis-AmazonReviews</i>

<h3>Introduction to project</h3>
<p>Project implementation includes configuring a cluster of Hadoop, and run a MapReduce batch job in the Hadoop daemons. <dfn>Hadoop</dfn> is basically an open source software framework used for distributed data storage and data processing. It uses MapReduce programming model for processing the data. Through this project we are elaborating power of Hadoop, regarding storing the data in the distributed manner and using parallel processing concepts.
<br>In our Project, we are running a MapReduce batch job to perform sentiment analysis on a sample set of data, which contains a review of Amazon product named “Kindle.” (We have grabbed the dataset from official website of Amazon). Further, we are using the Afnin.txt file for counting polarity of each tokenized reviews. Both the data sources are opensource. (both the data-sets are in the project repository)</br>
We are using Virtual Box for setting up a hadoop in psuedo distributed mode. The Virtual Box machine runs on Ubuntu 17.04 operating system. Further, we have configured Hadoop-2.8.1 in Virtual Box instance. 
The goal is to execute MapReduce batch job for Sentiment Analysis and provide the analysis result in Graphical format. </p>


<h3>Approach</h3>
<p>
We have used Ubuntu 17.04 as an underlying operating system of VirtualBox instance. Further, configured Hadoop-2.8.1 version in both of the VM instances. And set up the development environment in eclipse oxygen using “Hadoop-MapReduce-plugin-eclipse” in VM-Master node. We used AFNIN.txt (FINN is a list of English words rated for valence with an integer between -5 and +5) to count the sentiment polarity of reviews, and ProductReviews.csv as a data file, which contains reviews of  Amazon Product “Kindle E-reader.” Both the datasets are copyright protected and distributed under "Open Database License (ODbL) v1.0." 

A sample review (tab separated csv file):

<code><product name, author name, review text, ratings>
<br>“Kindle E-reader - Black, 6" Glare-Free Touchscreen Display, Wi-Fi -  Includes Special Offers MissOliviaP  <Review Text> 5.”</br></code> 

<i> <b>Review Text:</b>
 “I don't like this kindle.  I have had a kindle for six years,  and read daily but very rarely pick up a book anymore.  The Kindle that I had before just died.  It was the kind with the buttons on the side to turn the page.  I liked that much better.  Unfortunately, after six years,  the pages just kept flipping around, and I couldn't control it.  I don't like the touchscreen for turning the pages.  About a third of the time I have to touch it twice,  and a third of the time it skips ahead two pages, and I have to go back to see if I missed anything.  I didn't do my research to see if I could still get the old kind.  I just may have to do the that.”</i>

Our project is targeted to find, whether product reviews are overall positive, negative or neutral.

We wrote two chaining Mappers and Reducers for achieving the task. 

Mapper1 
<ul>•	setup a distributed cache (which has contents AFNIN.txt)</ul> 
<ul>•	Get datafile (ProductReviews.csv) as a command line argument.</ul> 
<ul>•	Tokenize <reviews> string, and compare tokens in the distributed hash table  if found then get the polarity of a token, and keep on adding the polarity.</ul>
 
Reducer1
<ul>•	Write intermediate output:</ul>
<code>•	<review text, original rating, rating value, newly counted polarity></code>

If Mapper1 and Reducer1 execute successfully, then the execution pointer will be set to Mapper2. (note that we will have intermediate output from Reducer2)

Mapper2
<ul>•	Get “newly counted polarity” and compare.</ul>
<ul>•	If = 0 then “natural” , if > 0 then “positive”, else “negative”.</ul>
<ul>•	For each record in intermediate output we will have either positive, negative or natural.</ul>

Reducer2
<ul>•	Count number of positives, negatives and natural.</ul>
<ul>•	Count will show overall sentiment analysis.</ul>
</p>







