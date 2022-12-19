/**
 * 
 */
var delForm;

 (gb = function(){ //즉시실행할 수 있도록 
//()() imediate 즉시 실행 
 
 var delForm;
 
 $('.btnSearch').on('click',function(){
	console.log('검색버튼 눌림');
	var frm =$('.frm_gb_search')[0];
	
	frm.nowPage.value=1;
	var param=$(frm).serialize();
	$('#section').load("/guestbook/guestbook_select",param);
});
 
 $('.btnPrev').on('click',function(){
		let frm =$('.frm_gb_search')[0];
		frm.nowPage.value=Number(frm.nowPage.value)-1;
		let param = $(frm).serialize();
		$('#section').load("/guestbook/guestbook_select",param);	
});
	
 $('.btnNext').on('click',function(){
		let frm =$('.frm_gb_search')[0];
		frm.nowPage.value=Number(frm.nowPage.value)+1;
		let param = $(frm).serialize();
		$('#section').load("/guestbook/guestbook_select",param);	
});	

$('.btnGuestbookSave').on('click',function(){
	let frm = $('.frm_guestbook_insert')[0];
	let param = $(frm).serialize();
	
	$.post('/guestbook/guestbook_insert', param, function(data){ 
		console.log('저장 눌러짐')
		//insert하고 나서 다시 검색 select해서 다시 뿌려줘 
		var frm =$('.frm_gb_search')[0];
		var param=$(frm).serialize();
		$('#section').load("/guestbook/guestbook_select",param);
		$('#section').html(data);
	});
});

	gb.modifyView = function(frm){
		let div = frm.querySelector('.UpdateZone');
		div.style.visibility='visible';
	}
	
	gb.cancel = function(frm){
		let div = frm.querySelector('.UpdateZone');
		div.style.visibility='hidden';
	}
	
	/* modal box ---------------------*/
	$('#btnClose').on('click',function(){
		$('#modal').css('display', 'none');
	})
	
	
	var delForm;
	
	gb.modalView = function(frm){
		delForm=frm;
		$('#modal').css('display', 'block');
	}
	
	/* 방명록 삭제 ---------------------------*/
	$('#btnCheck').on('click',function(){
		delForm.pwd.value=$('#pwd').val();
		
		 let frm = delForm;
      
       let param = $(frm).serialize();
       $.post('/guestbook/guestbook_delete',param,function(data){
           frm = $('.frm_gb_search')[0];
          param = $(frm).serialize();
          $('#section').load('/guestbook/guestbook_select',param);
          $('#section').html(data);
      });
   
   });

	
	gb.update=function(frm){
		console.log('수정 시작');
		let param= $(frm).serialize();
		
		$.post('guestbook/guestbook_update',param,function(data){
			frm = $('.frm_guestbook_search')[0];
			param=$(frm).serialize();
			$('#section').load("/guestbook/guestbook_select",param);
			$('#section').html(data);
		});
	};
	
})();

