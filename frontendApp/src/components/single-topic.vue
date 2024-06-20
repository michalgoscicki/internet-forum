<script setup lang="ts">
import type {Topic} from "~/types";

const props = defineProps<Topic>();
</script>

<template>
  <div class="bg-blue-400 px-2rem py-1rem rounded-2xl mt-1rem shadow-md mx-1/10">
    <p class="text-white font-bold text-md">{{ props.author.username }}</p>
    <p class="text-white font-bold text-2xl mt-2">{{ props.title }}</p>
    <p class="text-white font-bold text-md">{{ props.content }}</p>
  </div>
</template>
