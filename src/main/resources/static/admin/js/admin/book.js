$('document').ready(function() {
	$('.editButton').on('click', function(event){	
		event.preventDefault();
		var href= $(this).attr('href');		
		
		$.get(href, function(state, status){
			$('#idEdit').val(book.id);
			$('#imageEdit').val(book.image);
			$('#isbnEdit').val(book.isbn);
			$('#nameEdit').val(book.name);
			$('#descriptionEdit').val(book.description);
			$('#ddlAuthorEdit').val(book.authorid);
			$('#ddlCategoryEdit').val(book.categoyryid);
			$('#ddlPublisherEdit').val(book.publisherid);
		});			
		$('#editModal').modal();	
	});
	
	$('.table #deleteButton').on('click',function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delRef').attr('href', href);
		$('#deleteModal').modal();		
	});	
});