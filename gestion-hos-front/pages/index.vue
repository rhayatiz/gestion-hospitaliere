<template>
  <div class="bg-gray-200 h-full w-full">
    <div class="container mx-auto flex content-center h-full w-full flex">
      <div class="h1 font-light text-center mx-auto my-auto">
        <span>Hello from index vue</span><br>
        <button><a href="/login">login</a></button><br>
        <button><a href="/register">register</a></button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'IndexPage',
  mounted() {
    this.fetchData();
  },
  methods: {
    async fetchData(){
      const ip = await this.$axios.$get('/api/v1/user');
      console.log(ip);
    }  
  }
}
</script>

<style>
body, html, #__nuxt, #__layout {
  height: 100%;
  width: 100%;
}
</style>
