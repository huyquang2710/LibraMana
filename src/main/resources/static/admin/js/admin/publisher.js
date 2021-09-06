$('document').ready(function(){
	$('.editButton').on('click', function(event){	
		event.preventDefault();
		var href= $(this).attr('href');		
		
		$.get(href, function(publisher, status){
			$('#idEdit').val(publisher.id);
			$('#nameEdit').val(publisher.name);
			$('#phoneEdit').val(publisher.phone);
			$('#addressEdit').val(publisher.address);
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