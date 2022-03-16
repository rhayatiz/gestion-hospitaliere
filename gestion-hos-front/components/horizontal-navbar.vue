<template>
    <div class="user-navbar h-10 bg-gray-400 shadow-md">
        <div class="flex text-white justify-items-end h-full">
        <div class="ml-auto my-auto font-light pb-1">John Doe</div>
        <div class="mx-2 my-auto">
            <button class="bg-blue-700 hover:bg-blue-800 rounded-md px-2 pb-1 mx-2" @click="logout()">logout</button>
        </div>
        </div>
    </div>
</template>

<script>
export default {
    methods: {
        logout: function(){
            localStorage.removeItem('token');
            this.$store.dispatch("hello/logout");
            this.$router.push("/");
        }
    }
}
</script>

<style>

</style>