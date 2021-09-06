function chooseFile(fileInput) {
    if(fileInput.files && fileInput.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e){
            $('#thumbnail').attr('src', e.target.result);
        }
        reader.readAsDataURL(fileInput.files[0]);
    }
}
function clearFilter() {
	window.location = "/authors"
}