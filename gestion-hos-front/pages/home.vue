<template>
  <div>
      
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    <div class="flex flex-row">
      <vertical-navbar />
      <main class="w-full">
        <horizontal-navbar />
        second div here
      </main>

    </div>
  </div>
</template>

<script>
export default {
    middleware: 'authenticated'
}
</script>

<style>

</style>