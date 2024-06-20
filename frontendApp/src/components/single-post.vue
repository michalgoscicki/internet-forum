<script setup lang="ts">
import type {Post} from "~/types"

const props = defineProps<Post>()
const topicStore = useTopicStore()
const auth = useAuthStore()

const editPostVisible = ref(false)
const changeVisible = () => {
  editPostVisible.value = !editPostVisible.value
}

const deletePost = async () => {
  const route = useRoute()
  const topicId = parseInt(<string>route.params.id)
  await topicStore.deletePost(topicId, props.id)
  await props.refresh()
}
</script>

<template>
  <div class="bg-blue-500 px-2rem py-1rem rounded-2xl mt-1rem shadow-md mr-1/10 ml-1/8">
    <div class="flex">
      <p class="text-gray-300 font-bold text-md">{{ props.author.username }}</p>
      <Icon @click="changeVisible" name="mdi:application-edit" v-if="props.author.id === auth.user.id || auth.user.roles.some(role => role.id === 2)"
            class="text-black text-2xl mx-2"/>
      <Icon @click="deletePost" v-if="props.author.id === auth.user.id || auth.user.roles.some(role => role.id === 2)"
            name="mdi:delete-forever" class="text-black text-2xl"/>
    </div>

    <post-edition-form v-if="editPostVisible" :post-id="props.id" :content="props.content" :refresh="props.refresh" :reject-changes="changeVisible" />
    <p v-else class="text-white font-bold whitespace-pre-line text-md">{{ props.content.trim() }}</p>
  </div>
</template>
