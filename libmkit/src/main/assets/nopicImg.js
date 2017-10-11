/*var gifFlags = Android Send;
*/
function displaymessage(id, srb) {
	var openImgFlag = document.getElementById(id).getAttribute("openImgFlag");
	if (openImgFlag == 'true') {
		document.getElementById(id).src = srb;
		document.getElementById(id).setAttribute("openImgFlag","false");
	} else {
		javascriptInterface.openImage(id, id + srb);
	}
	var index = id;

	$('.picture').eq(index).on('load', function() {

		var imgSrc = $('.picture').eq(index).attr('src');
		var datasrc = $('.picture').eq(index).attr('datasrc');
		if (imgSrc != datasrc) {
			$('.gif').eq(index).css('display', 'none');
		}

	});
}
window.onload = function() {
	var len = $('.position').length;
	for (var i = 0; i < len; i++) {
		var gifLeft = $('.picture').eq(i).width() / 2 - 28 + 'px';
		var gifTop = $('.picture').eq(i).height() / 2 - 28 + 'px';
		$('.gif').eq(i).css({
			'left': gifLeft,
			'top': gifTop
		}).attr('gifMark', gifFlags[i]);
		if (gifFlags[i] == 1) {

			$('.gif').eq(i).css({
				'display': 'block'
			});
		}
	}
	$('.picture').on('error', function() {
		$(this).attr('errorFlag', 1);

		$(this).attr('src', "file:///android_asset/trans.png");
	});
	$('.position').on('touchend', function() {
		var index = $(".position").index(this);
		var clickCount = $(".position").eq(index).attr('clickCount');
		var errorFlag = $('.picture').eq(index).attr('errorFlag');
		if (clickCount) {
			if (errorFlag && errorFlag != 1) {
				$('.gif').eq(index).css('display', 'none');
			}
			return false;
		} else {
			$(".position").eq(index).attr('clickCount', '1');
		}
		var gifMark = $('.gif').eq(index).attr('gifMark');
		if (gifMark == 1) {
			$('.gif').eq(index).attr('src', 'file:///android_asset/loading.png');
		} else {
			$('.gif').eq(index).css('display', 'block');
		}
	});

}