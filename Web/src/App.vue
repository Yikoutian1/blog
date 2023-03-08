<template>
  <div id="app">
    <router-view/>
    <!-- <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div> -->

  </div>
</template>

<style>
html,body {
  width: 100%;
  min-height: 100%;
  background: #fff;
}
body,ol,ul,h1,h2,h3,h4,h5,h6,p,th,td,dl,dd,form,fieldset,legend,input,textarea,select,figure,figcaption{	margin:0;	padding:0;}
h1,h2,h3,h4,h5,h6,b,strong{	font-size: 100%;	font-weight: normal;}
i,em{	font-style: normal;}
li{	list-style: none;}
a{	color: #333;	text-decoration: none;}
body,html{	font: 14px "微软雅黑",Arial;	color: #333;}
input{	outline: none;}
.clearfix{	*zoom:1;}
.clearfix:after{	content: '';	display:block;	height: 0;	overflow: hidden;	clear: both;}

</style>