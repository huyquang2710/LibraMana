$('document').ready(function(){
	$('.editButton').on('click', function(event){	
		event.preventDefault();
		var href= $(this).attr('href');		
		
		$.get(href, function(category, status){
			$('#idEdit').val(category.id);
			$('#codeEdit').val(category.code);
			$('#nameEdit').val(category.name);
		});			
		$('#editModal').modal();	
	});
	
	$('.table #deleteButton').on('click',function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#confirmDeleteButton').attr('href', href);
		$('#deleteModal').modal();		
	});
});