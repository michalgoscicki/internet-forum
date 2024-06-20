<script setup lang="ts">
import type {Topic} from "~/types";

const props = defineProps<Topic>();
const topicStore = useTopicStore()
const auth = useAuthStore()
const route = useRoute()

const editTopicVisible = ref(false)
const changeVisible = () => {
  editTopicVisible.value = !editTopicVisible.value
}

const deleteTopic = async () => {
  const router = useRouter()
  await topicStore.deleteTopic(props.id)
  await topicStore.refreshTopicList()
  await router.push('/topics')
}
</script>

<template>
  <div class="bg-blue-400 px-2rem py-1rem rounded-2xl mt-1rem shadow-md mx-1/10">
    <div class="flex">
      <p class="text-gray-300 font-bold text-md">{{ props.author.username }}</p>
      <Icon @click="changeVisible" name="mdi:application-edit" v-if="(props.author.id === auth.user.id || auth.user.roles.some(role => role.id === 2))  && route.name !== 'topics'" class="text-black text-2xl mx-2"/>
      <Icon @click="deleteTopic" name="mdi:delete-forever" v-if="(props.author.id === auth.user.id || auth.user.roles.some(role => role.id === 2)) && route.name !== 'topics'" class="text-black text-2xl"/>
    </div>
    <topic-edition-form v-if="editTopicVisible" :title="props.title" :content="props.content" :topicId="props.id" :reject-changes="changeVisible" :refresh="props.refresh" />
    <div>
      <p class="text-white font-bold whitespace-pre-line text-2xl mt-2">{{ props.title.trim() }}</p>
      <p class="text-white font-bold whitespace-pre-line text-md">{{ props.content.trim() }}</p>
    </div>
  </div>

</template>
