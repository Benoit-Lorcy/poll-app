<template>
  <v-container>
    <v-row class="justify-center">
      <v-col
          cols="12"
          md="6"
      >
        <v-sheet
            border
            rounded
            style="text-align: center"
            class="pa-4"
        >
          <v-form validate-on="submit" @submit.prevent="submit">
            <h1 class="pb-4"> {{poll.pollName}}</h1>
              <div class="d-flex flex-column mb-4">
                <template v-for="(option, index) in poll.pollOption" :key="index">
                  <v-btn
                    @click="flipButton(index)"
                    variant="outlined"
                    color="blue"
                    class="ma-2"
                    :active="option.trigger?? false"
                  >{{option.label}}
                  </v-btn>
                </template>
              </div>
              <v-btn
                  :loading="loading"
                  type="submit"
                  color="blue"
                  class="mb-4"
              >
                Vote
              </v-btn>
            <v-spacer></v-spacer>
              <v-btn
                  @click="router.push('/result/' + route.params.id)"
                  color="blue"
                  variant="tonal"
                  class="mb-4"
              >
                View Results without voting
              </v-btn>
          </v-form>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup lang="ts">
  import {ref} from "vue";
  import {useRoute, useRouter} from "vue-router";
  import {config} from '@/config';

  const router = useRouter();
  const route = useRoute();
  const poll = await getPollRequest();
  const loading = ref(false);

  async function getPollRequest() {
    try {
      const response = await fetch(`${config.apiUrl}/v1/polls/${route.params.id}`);

      if (!response.ok) {
        throw new Error('HTTP error ' + response.status);
      }

      const data : pollInt = await response.json();

      return ref(data); // Log the response data for debugging, remove or change this as needed
    } catch (error) {
      console.error('Failed to get data:', error);
      const fake = {} as pollInt;
      return ref(fake) ;
    }
  }

  function flipButton(index : number){
    for(let p of poll.value.pollOption){
      p.trigger = false;
    }
    poll.value.pollOption[index].trigger = true;
  }
  async function submit(){
    loading.value = true
    const sent = await sendVoteRequest()
    loading.value = false
    if(sent){
      await router.push('/result/' + route.params.id)
    }
  }

  async function sendVoteRequest(){
    let option = poll.value.pollOption.find(opt => opt.trigger === true);
    if(!option) return false;

    try {
      const response = await fetch(
          `${config.apiUrl}/v1/polls/vote/${route.params.id}/option/${option.optionId}`,
          {method: 'POST'}
      );

      if (!response.ok) {
        throw new Error('HTTP error ' + response.status);
      }


      return true; // Log the response data for debugging, remove or change this as needed
    } catch (error) {
      console.error('Failed to get data:', error);
      return false;
    }
  }

  interface pollInt {
    pollName: string;
    authorName: string;
    pollOption: {
      optionId: string;
      label: string;
      voteCount: number;
      trigger: boolean;
    }[];
  }
</script>

<style scoped>

</style>