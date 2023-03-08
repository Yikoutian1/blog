<template>
  <div>
    <button @click="handled">You clicked me {{ count }} times.</button>
  </div>
</template>
<script>
export default {
  data(){
    return {
      numbers:0
    }
  },
  props:{
    count:{
      type:Number,
      default: 1000
    }
  },
  methods:{
    handled(){
      this.numbers = this.count
      this.$emit("on-submit",++this.numbers)
    }
  }
}

</script>