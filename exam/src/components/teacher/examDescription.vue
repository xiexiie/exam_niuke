<template>
  <div class="recommendPage">
    <swiper :options="swiperOption" ref="mySwiper">
      <swiper-slide>
        <a href="https://xietongxue.top">
          <img src="../../assets/img/1.jpg" class="img" >
        </a>
        <div class="text">点击图片有惊喜！</div>
      </swiper-slide>
      <swiper-slide>
        <a href="https://leetcode-cn.com/problemset/all/">
          <img src="../../assets/img/2.jpg" class="img" >
        </a>
        <div class="text">点击图片康康跳哪儿去了！</div>
      </swiper-slide>
      <swiper-slide>
        <a href="https://www.zhihu.com/">
          <img src="../../assets/img/3.jpg" class="img" >
        </a>
        <div class="text">看我到哪儿去了！</div>
      </swiper-slide>
      <div class="swiper-pagination" slot="pagination"></div>
      <div class="swiper-button-prev" slot="button-prev"></div>
      <div class="swiper-button-next" slot="button-next"></div>
      <div class="swiper-scrollbar" slot="scrollbar"></div>
    </swiper>
  </div>
</template>

<script>
  export default {
    name: 'carrousel',
    data() {
      return {
        swiperOption: {
          //direction: 'vertical', // 垂直切换选项
          loop: true, // 循环模式选项
          speed: 1000,  //设置slide从自动滑动开始到结束的时间(单位ms)
          autoplay: {  //设置轮播图自动播放
            delay: 1000,
            stopOnLastSlide: true,
            disableOnInteraction: false
          },
          // 显示分页
          pagination: {
            el: ".swiper-pagination",
            clickable: true, //允许分页点击跳转
            type: 'bullets',
            dynamicBullets: false
          },
          effect: 'slide', //图片切换效果： 滑动
          // 如果需要前进后退按钮
          navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev"
          },
          // 如果需要滚动条
          scrollbar: {
            el: '.swiper-scrollbar',
          },
        }
      };
    },
    computed: {
      swiper() {
        return this.$refs.mySwiper.swiper;
      }
    },
    mounted() {
      // current swiper instance
      // 然后你就可以使用当前上下文内的swiper对象去做你想做的事了
      console.log("this is current swiper instance object", this.swiper);
      this.swiper.slideTo(3, 1000, false);
    }
  };
</script>
<style scoped >
  .recommendPage .swiper-container{
    position: relative;
    width: 100%;
    height: 500px;
    /*background: pink;*/
  }
  /*.recommendPage .swiper-container .swiper-slide{*/
  /*  width: 100%;*/
  /*  line-height: 200px;*/
  /*  !*background: yellowgreen;*!*/
  /*  !*color: #000;*!*/
  /*  !*font-size: 16px;*!*/
  /*  !*text-align: center;*!*/
  /*}*/
  .recommendPage .img{
    position: center;
    width: 100%;
    height: 460px;
  }
  .recommendPage .text{
    font-size: 16px;
    text-align: center;
  }
</style>
