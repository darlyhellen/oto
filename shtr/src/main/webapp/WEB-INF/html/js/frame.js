$(document).ready(function() {
	$("p").hide();
	$("h2").hide();
	$("p").click(function() {
				$(this).hide();
			});
	$("h2").click(function() {
				if ($("p").is(":hidden")) {
					$("p").show();
				} else {
					$("p").hide();
				}
			});
	$("#idt1").click(function() {
		if (!$(".idt1").is(":hidden")) {
			$("#idt_im1").attr("src", "/shtr/html/images/arrow_gray_right.png");
			$(".idt1").hide();
		} else {
			$("#idt_im1").attr("src", "/shtr/html/images/arrow_gray_down.png");
			$(".idt1").show();
		}
		console.log("idt1:%s", $(".idt1").is(":hidden"));
	})
	$("#idt2").click(function() {
		if (!$(".idt2").is(":hidden")) {
			$("#idt_im2").attr("src", "/shtr/html/images/arrow_gray_right.png");
			$(".idt2").hide();
		} else {
			$("#idt_im2").attr("src", "/shtr/html/images/arrow_gray_down.png");
			$(".idt2").show();
		}
		console.log("idt2:%s", $(".idt2").is(":hidden"));
	})
	$("#checkuser").click(function() {
				window.parent.rightframe.location = "/shtr/server/seruser";
			})
	$("#checkads").click(function() {
				window.parent.rightframe.location = "/shtr/html/bodyshow.html";
			})
	$("#checkcloud").click(function() {
				window.parent.rightframe.location = "/shtr/html/bodyshow.html";
			})
	$("#checkgod").click(function() {
				window.parent.rightframe.location = "/shtr/html/bodyshow.html";
			})
	$("#changepass").click(function() {
				window.parent.rightframe.location = "/shtr/html/contenct.html";
			})
	$("#changeset").click(function() {
				window.parent.rightframe.location = "/shtr/html/contenct.html";
			})
	$("#aboutus").click(function() {
				window.parent.rightframe.location = "/shtr/html/contenct.html";
			})

	$("#label_back").click(function() {
				window.parent.location = "/shtr/index.jsp";
				console.log("label_back:%s", "label_back");
			})
});