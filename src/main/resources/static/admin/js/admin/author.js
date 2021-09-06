$('document').ready(function(){
	$('.editButton').on('click', function(event){	
		event.preventDefault();
		var href= $(this).attr('href');		
		
		$.get(href, function(author, status){
			$('#idEdit').val(author.id);
			$('#imageEdit').val(author.image);
			$('#nameEdit').val(author.name);
			$('#ageEdit').val(author.age);
			$('#phoneEdit').val(author.phone);
			$('#addressEdit').val(author.address);
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