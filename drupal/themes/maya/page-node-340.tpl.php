<?php
// $Id: page.tpl.php,v 1.7 2009/07/03 15:09:30 nbz Exp $
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="<?php print $language->language ?>" dir="<?php print $language->dir ?>">
  <head>
    <title><?php print $head_title ?></title>
    <?php print $head ?>
    <?php print $styles ?>
    <?php print $scripts ?>
    <script type="text/javascript" src="/sites/all/themes/maya/js/scripts.js"></script>
    <link rel="stylesheet" href="/sites/all/themes/maya/css/slideshow.css" type="text/css" media="screen" />
  </head>
  <body>
    <div class="PageBackgroundGlare">
    <div class="PageBackgroundGlareImage"></div>
    </div>
    <div id="head" class="clearfloat">
      <div class="clearfloat">
      <div id="google_translate_element"></div>
      <script>
           function googleTranslateElementInit() {
           new google.translate.TranslateElement({
           pageLanguage: 'en',
           includedLanguages: 'en,fr,pt,es'
           }, 'google_translate_element');
           }
     </script><script src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit"></script>

     <div id="logo">
          <?php if ($logo || $site_name) {
            print '<a href="'. check_url($base_path) .'" title="'. $site_name .'">';
            if ($logo) {
              print '<img src="'. check_url($logo) .'" alt="'. $site_name .'" />';
          } else {
            print '<span id="sitename">'. $site_name .'</span>';
          }
            print '</a>';
          }
        ?>
        <?php if ($site_slogan): print '<div id="tagline">'. $site_slogan .'</div>'; endif; ?>
        </div>

        <?php if ($banner): ?>
          <div id="banner-region">
            <?php print $banner; ?>
          </div>
        <?php endif; ?>
      </div>

      <div id="navbar" class="clearfloat">
          <?php 
            print maya_primary($primary_links);
          ?>
        <?php if ($search_box): ?>
          <div id="searchform"><?php print $search_box; ?></div>
        <?php endif; ?>
      </div>
    </div>
    <div id="navi-top"> </div>
    <div id="page" class="clearfloat">
<!--content featured gallery end -->
    <div id="header"><div class="wrap">
    <div id="slide-holder">
    <div id="slide-runner">
        <a href="http://gerardoramirez.info/mexico/chichen-itza"><img id="slide-img-1" src="/sites/all/themes/maya/images/slideshow/chichenitza.jpg"  class="slide" alt="" /></a>
        <a href="http://gerardoramirez.info/colombia"><img id="slide-img-2" src="/sites/all/themes/maya/images/slideshow/bolivar.jpg" class="slide" alt="" /></a>
        <a href="http://gerardoramirez.info/peru"><img id="slide-img-3" src="/sites/all/themes/maya/images/slideshow/machupicchu.jpg" class="slide" alt="" /></a>
        <a href="http://gerardoramirez.info/japan/hiroshima-building"><img id="slide-img-4" src="/sites/all/themes/maya/images/slideshow/hiroshima.jpg" class="slide" alt="" /></a>
        <a href=""><img id="slide-img-5" src="/sites/all/themes/maya/images/slideshow/che.jpg" class="slide" alt="" /></a>
        <div id="slide-controls">
         <p id="slide-client" class="text"><strong>post: </strong><span></span></p>
         <p id="slide-desc" class="text"></p>
         <p id="slide-nav"></p>
        </div>
    </div>
<!--content featured gallery end -->
   </div>
   <script type="text/javascript">
    if(!window.slider) var slider={};slider.data=[
        {"id":"slide-img-1","client":"Chichen Itza | Yucatán, Mexico, ","desc":"Our History can only be saved by retaining our culture"},
        {"id":"slide-img-2","client":"Simon Bolivar","desc":"Latin Americas Hero"},
        {"id":"slide-img-3","client":"Machu Picchu | Cusco, Peru","desc":"Our past has to be taught and shared to our generations"},
        {"id":"slide-img-4","client":"A Bomb Building | Hiroshima, Japan","desc":"Abolition of nuclear weapons is a global responsibility"},
        {"id":"slide-img-5","client":"Ernesto Che Guevara","desc":"Hasta la Victoria Siempre"}];
   </script>
  </div></div><!--/header-->

  
      <?php if ($headline): ?>
      <div id="top" class="clearfloat">
        <div id="headline" class="<?php print empty($featured)? 'no' : 'with'?>-featured">
          <?php print $headline; ?>
        </div>
        <?php if ($featured): ?>
          <div id="featured">
            <?php print $featured; ?>
          </div>
        <?php endif; ?>
      </div>
      <?php endif; ?>

      <?php if ($middle):?>
        <div id="middle" class="clearfloat">
          <?php print $middle; ?>
        </div>
      <?php endif; ?>
      
      <div id="content" class="main-content <?php print empty($sidebar)? 'no' : 'with'?>-sidebar">

      <?php if ($content_top): ?>
          <div id="content-top">
            <?php print $content_top; ?>
          </div>
        <?php endif; ?>
        <!--div class="esa-logo"></div-->
        <?php if ($breadcrumb) { print $breadcrumb; } ?>
        <?php if ($mission) { print "<div id='mission'>". $mission ."</div>"; } ?>
        <?php if ($tabs) { print "<div id='tabs-wrapper' class='clear-block'>"; } ?>
        <?php if ($title) { print "<h1". ($tabs ? " class='with-tabs'" : "") .">". $title ."</h1>"; } ?>
        <?php if ($tabs) { print $tabs ."</div>"; } ?>
        <?php if (isset($tabs2)) { print $tabs2; } ?>
        <?php if ($help) { print $help; } ?>
        <?php if ($show_messages && $messages) { print $messages; } ?>
        <?php print $content; ?>

        <?php if ($content_bottom): ?>
          <div id="content-bottom">
            <?php print $content_bottom; ?>
          </div>
        <?php endif; ?>

      </div>

      <?php if ($sidebar):?>
        <div id="sidebar">
          <?php print $sidebar; ?>
        </div>
      <?php endif; ?>

      <?php if ($bottom):?>
        <div id="bottom">
          <?php print $bottom; ?>
        </div>
      <?php endif; ?>
    </div>
    <div id="navi-top"> </div>
    <div id="footer-region" class="clearfloat">
      <div id="footer-left" class="clearfloat">
        <?php print $footer_left; ?>
      </div>

      <div id="footer-middle" class="clearfloat">
        <?php print $footer_middle; ?>
      </div>

      <div id="footer-right" class="clearfloat">
        <?php print $footer_right; ?>
      </div>

      <div id="footer-end" class="clearfloat">
        <?php print $footer_end; ?>
      </div>
    </div>

    <div id="footer-message">
      <?php print $footer_message; ?>
      <?php  print maya_secondary_footer($secondary_links); ?>
    </div>
    <?php print $closure; ?>
  </body>
</html>
