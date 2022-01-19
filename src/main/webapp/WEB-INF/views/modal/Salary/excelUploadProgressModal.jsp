
<style>
.modal {
	position: absolute;
	width: 100%;
	height: 100%;
	z-index: 999;
	background: rgba(100, 100, 100, 0.8);
}
</style>

<script>
	function progressTest(){
	
		var obj = new Object();
		
		$.ajax({
			url:"CheckProgressExcel",
			type:"POST",
			dataType:"json",
			contentType:"application/json",
			data:JSON.stringify(obj),
			success: function(data){
				$('#excelProgress').css('width',data.percent+'%');
			},
			});
	}
</script>

<div class="modal" id="progress_modal" data-backdrop="static"
	data-keyboard="false" tabindex="-1" role="dialog"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered modal-lg">
		<div class="modal-content">
			<div class="modal-header" style="vertical-align: middle;">
				<h5 class="modal-title" id="exampleModalLongTitle">進行中</h5>
			</div>
			<div class="modal-body">
				<div class="progress">
					<div class="progress-bar progress-bar-striped progress-bar-animated" id="excelProgress" role="progressbar" style="width: 0%"
						aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
				</div>
				少々お待ちください。
			</div>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>

