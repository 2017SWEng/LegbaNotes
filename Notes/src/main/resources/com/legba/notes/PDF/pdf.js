/**
 * Works with the control of pdf viewer 
 * @author: vc622 rh1271 hjew501
 */

//variables 
var pageNumber = 1; 	//current page	 
var PDF = null; 		//stores PDF
var numOfPages = null;	//stores number of pages in PDF

 function loadpdf(url)
 {
   document.body.style.backgroundColor="#535360";
		   
   var pdfjs = window['pdfjs-dist/build/pdf'];
   
   //Loads the pdf at the url passed in
   var loadingTask = pdfjs.getDocument(url);
	loadingTask.promise.then(function(pdf) {

		//Stores the loaded pdf
		PDF = pdf;
		//Gets number of pages in pdf
		numOfPages = pdf.numPages;
		
		// Fetches the first page
		pdf.getPage(pageNumber).then(function(page) {
			changepage();
			});
			
		}, function (reason) {
			// PDF loading error
			console.error(reason);
		});
 }
 
 //Displays the next page if not on the last page
 function nextpage()
 {
	if (pageNumber < numOfPages) {
		pageNumber += 1;
		changepage();
	}
 }
 
 //Displays the previous page if not on the first page
 function previouspage()
 {
	if (pageNumber > 1) {
		pageNumber  -= 1;
		changepage(); 	
	}
 }
 
 //Displays the first page
 function firstpage()
 {
	pageNumber = 1;
	changepage();
 }
 
 //Displays the last page
 function lastpage()
 {
	pageNumber = numOfPages;
	changepage();
 }
 
 function changepage()
 {
	PDF.getPage(pageNumber).then(function(page) {

		//Sets scale and viewport of pdf and pane
		var scale = 1;
		var viewport = page.getViewport(scale);

		// Prepare canvas using PDF page dimensions
		var canvas = document.getElementById('the-canvas');
		var context = canvas.getContext('2d');
		canvas.height = viewport.height;
		canvas.width = viewport.width;

		// Render PDF page into canvas context
		var renderContext = {
			canvasContext: context,
			viewport: viewport
		};
		var renderTask = page.render(renderContext);
		renderTask.then(function () {});
	});		 	
 
 }
 
 function getPageNumber(){
 	return pageNumber;
 }
 
function getNumberPages(){
 	return numOfPages;
 }
 
 function setPageNumber(newPageNumber){
 	pageNumber = newPageNumber;
	changepage();
 }