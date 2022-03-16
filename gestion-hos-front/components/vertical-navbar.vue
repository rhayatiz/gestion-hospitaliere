<template>
        <nav class="w-16 bg-gray-700 h-screen">
        <div class="flex flex-col">
          <div class="mx-auto mt-2 relative">
            <a :href="$nuxt.$route.path == '/home' ? '#' : '/home'">
              <i class="fas fa-home
                                rounded-xl
                                p-3 text-gray-100
                                text-lg"
                :class="$nuxt.$route.path == '/home' ? 'bg-gray-800' : 'bg-gray-600 hover:bg-gray-500'"></i>
            </a>
          </div>


          <div class="mx-auto mt-2 relative">
            <a :href="$nuxt.$route.path == '/patients' ? '#' : '/patients'">
              <i class="fas fa-users
                                rounded-xl
                                p-3 text-gray-100 bg-gray-600
                                text-lg"
                :class="$nuxt.$route.path == '/patients' ? 'bg-gray-800' : 'bg-gray-600 hover:bg-gray-500'"></i>
                                
            </a>
          </div>


          <div class="mx-auto mt-2 relative">
            <a href="#">
              <i class="far fa-file-alt
                                rounded-xl
                                p-3 text-gray-100 bg-gray-600
                                text-lg"></i>
            </a>
          </div>
        </div>
      </nav>
</template>

<script>
export default {

}
</script>

<style>
@tailwind base;
@tailwind utilities;
@tailwind components;
</style>