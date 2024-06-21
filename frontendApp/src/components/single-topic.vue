<script setup lang="ts">
import type {Follower, Topic} from "~/types";

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

const followTopic = async () => {
  isFollower.value = !isFollower.value
  await topicStore.followTopic(props.id)
  await props.refresh()
  await topicStore.refreshFollowedTopicList()
}

const unfollowTopic = async () => {
  isFollower.value = !isFollower.value
  await topicStore.unfollowTopic(props.id)
  await props.refresh()
  await topicStore.refreshFollowedTopicList()
}

const dialogVisible = ref(false)

const changeDialogVisible = () => {
  dialogVisible.value = !dialogVisible.value
}

const isFollower = ref(false)

const {data: followers} = await useFetchApi<Follower[]>(`/api/v1/topics/${props.id}/followers`, {
  headers: {Authorization: 'Bearer ' + auth.token},
})

watchEffect(() => {
  if (followers.value) {
    isFollower.value = followers.value.some((follower) => follower.id === auth.user.id)
  }
})
</script>

<template>
  <div class="bg-blue-400 px-2rem py-1rem rounded-2xl mt-1rem shadow-md mx-1/10">
    <div class="flex">
      <p class="text-gray-300 font-bold text-md">{{ props.author.username }}</p>
      <Icon @click="changeVisible" name="mdi:application-edit" v-if="(props.author.id === auth.user.id || auth.user.roles.some(role => role.id === 2))  && route.name === 'topics-id'" class="text-black text-2xl mx-2 hover:animate-bounce"/>
      <Icon @click="deleteTopic" name="mdi:delete-forever" v-if="(props.author.id === auth.user.id || auth.user.roles.some(role => role.id === 2)) && route.name === 'topics-id'" class="text-black text-2xl hover:animate-bounce"/>
      <Icon @click="followTopic" name="mdi:bookmark-plus" v-if="route.name === 'topics-id' && !isFollower" class="text-black text-2xl mx-2 hover:animate-bounce"/>
      <Icon @click="unfollowTopic" name="mdi:bookmark-minus" v-if="route.name === 'topics-id' && isFollower" class="text-black text-2xl mx-2 hover:animate-bounce"/>
      <Icon @click="changeDialogVisible" name="material-symbols:warning-rounded" v-if="route.name === 'topics-id'" class="text-black text-2xl hover:text-rose-500"/>
    </div>
    <topic-edition-form v-if="editTopicVisible" :title="props.title" :content="props.content" :topicId="props.id" :reject-changes="changeVisible" :refresh="props.refresh" />
    <div>
      <p class="text-white font-bold whitespace-pre-line text-2xl mt-2">{{ props.title.trim() }}</p>
      <p class="text-white font-bold whitespace-pre-line text-md">{{ props.content.trim() }}</p>
      <report-dialog v-if="dialogVisible" :id="props.id" @close="changeDialogVisible"  />
    </div>
  </div>

</template>
