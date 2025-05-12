/*each 향상된 반복문 같은 역할  */
$('#select[data-value]').each(function(index, item) {
	const items = $(item);
	const defaultValue = items.attr('data-value').trim();
	
	if(defaultValue.length > 0) {
		items.val(defaultValue);
		console.log(items);
	}
});