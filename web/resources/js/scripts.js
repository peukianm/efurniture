/*
    Slider
*/
$(window).load(function() {
    $('.flexslider').flexslider({
        animation: "slide",
        controlNav: true

    });
});


/*
    Filterable portfolio - Quicksand
*/

jQuery(document).ready(function() {
    console.log("quicksand is ready");
    $clientsHolder = $('ul.portfolio-img');
    $clientsClone = $clientsHolder.clone();

    console.log("$clientsHolder", $clientsHolder);
 
    $('.filter-portfolio a').click(function(e) {
        e.preventDefault();
        $filterClass = $(this).attr('class');

        console.log("$filterClass: ", $filterClass);
 
        $('.filter-portfolio a').attr('id', '');
        $(this).attr('id', 'active-imgs');
 
        if($filterClass == 'all'){
            $filters = $clientsClone.find('li');
        }
        else {
            $filters = $clientsClone.find('li[data-type~='+ $filterClass +']');
        }
 
        $clientsHolder.quicksand($filters, {duration: 700}, function() {
            $("a[rel^='prettyPhoto']").prettyPhoto({social_tools: false});
        });
    });
});


/*
    Pretty Photo
*/
jQuery(document).ready(function() {
    console.log("prettyphoto is ready");
    $("a[rel^='prettyPhoto']").prettyPhoto({social_tools: false});
});

/*
    Flickr feed
*/

$(document).ready(function() {
    $('.flickr-feed').jflickrfeed({
        limit: 8,
        qstrings: {
            id: '52617155@N08'
        },
        itemTemplate: '<li><a href="{{link}}" target="_blank"><img src="{{image_s}}" alt="{{title}}" /></a></li>'
    });
});


/*
    Google maps
*/
jQuery(document).ready(function() {
    console.log("google maps is ready");
    var position = new google.maps.LatLng(39.36824690000001, 22.935360800000016);
    $('.map').gmap({'center': position,'zoom': 15, 'disableDefaultUI':true, 'callback': function() {
            var self = this;
            self.addMarker({'position': this.get('map').getCenter() }); 
        }
    }); 
});


/*
    Contact form
*/
jQuery(document).ready(function() {
    $('.contact-form form').submit(function() {

        $('.contact-form form .nameLabel').html('Name');
        $('.contact-form form .emailLabel').html('Email');
        $('.contact-form form .messageLabel').html('Message');

        var postdata = $('.contact-form form').serialize();
        $.ajax({
            type: 'POST',
            url: 'assets/sendmail.php',
            data: postdata,
            dataType: 'json',
            success: function(json) {
                if(json.nameMessage != '') {
                    $('.contact-form form .nameLabel').append(' - <span class="violet" style="font-size: 13px; font-style: italic"> ' + json.nameMessage + '</span>');
                }
                if(json.emailMessage != '') {
                    $('.contact-form form .emailLabel').append(' - <span class="violet" style="font-size: 13px; font-style: italic"> ' + json.emailMessage + '</span>');
                }
                if(json.messageMessage != '') {
                    $('.contact-form form .messageLabel').append(' - <span class="violet" style="font-size: 13px; font-style: italic"> ' + json.messageMessage + '</span>');
                }
                if(json.nameMessage == '' && json.emailMessage == '' && json.messageMessage == '') {
                    $('.contact-form form').fadeOut('fast', function() {
                        $('.contact-form').append('<p><span class="violet">Thanks for contacting us!</span> We will get back to you very soon.</p>');
                    });
                }
            }
        });
        return false;
    });
});

/*
Full Page Background and slideshow
*/
 
 /* DISABLED
  $.backstretch([
      "assets/img/full_page_bg_1.jpg"
    , "assets/img/full_page_bg_2.jpg"
    , "assets/img/full_page_bg_3.jpg"
  ], {duration: 6000, fade: 1000});
*/

$(document).ready(function(){
    $("[rel=tooltip]").tooltip({ placement: 'right'});
});

$(document.getElementById( "modal1" )).change(function () {
    var str = "";
  
    str = ($(document.getElementById( "modal1" )).val());
    console.log(str);
    if(str == 1){
      $("#newChar").show();
    }
    else
      $("#newChar").hide();
});

$(document.getElementById( "modal2" )).change(function () {
    var str = "";
  
    str = ($(document.getElementById( "modal2" )).val());
    console.log(str);
    if(str == 1){
      $("#newChar2").show();
    }
    else
      $("#newChar2").hide();
});


$('#collapseOne').on('show', function () {
  $("#more").text('λιγότερα...');
})


$('#collapseOne').on('hide', function () {
  $("#more").text('περισσότερα...');
})