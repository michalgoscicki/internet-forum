<script setup lang="ts">
import type {Post} from "~/types"

const props = defineProps<Post>()
</script>

<template>
  <div class="bg-blue-500 px-2rem py-1rem rounded-2xl mt-1rem shadow-md mr-1/10 ml-1/8">
    <p class="text-white font-bold text-md">{{ props.author.username }}</p>
    <p class="text-white font-bold text-md">{{ props.content }}</p>
  </div>
</template>
