<template>
  <v-container>
    <v-form validate-on="submit" @submit.prevent="submit">
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
            <h1 class="pb-4"> Create you poll ! </h1>
            <v-text-field
                v-model="pollName"
                label="Poll Title"
                variant="outlined"
                density="compact"
                style="margin-right: auto"
                required
            />
            <v-text-field
                v-model="pollAuthor"
                label="Poll Author"
                variant="outlined"
                density="compact"
                required
            />
            <h2 class="pb-4">Options</h2>
            <template v-for="(option, index) in pollOptions" :key="index">
              <v-text-field
                v-model="pollOptions[index]"
                :label="'Option ' + (index + 1)"
                variant="outlined"
                density="compact"
                required
              />
            </template>
            <v-btn
                color="blue"
                variant="tonal"
                @click="addOption"
                class="p-0"
            >
              +
            </v-btn>
            <v-btn
                color="blue"
                variant="tonal"
                @click="deleteOption"
            >
              -
            </v-btn>
            <div class="d-flex flex-column">
              <v-btn
                  :loading="loading"
                  type="submit"
                  color="blue"
                  size="large"
                  class="mt-4"
              >
                Create poll
              </v-btn>
            </div>
          </v-sheet>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>

<script setup lang="ts">
  import {ref} from "vue";
  import {useRouter} from "vue-router";
  import {config} from "@/config";

  const pollName = ref("");
  const pollAuthor = ref("");
  const pollOptions = ref(["","",""]);
  const loading = ref(false);
  const router = useRouter()

  function addOption(){
    pollOptions.value.push("");
  }

  function deleteOption(){
    pollOptions.value.pop();
  }

  async function submit() {
    loading.value = true

    const result = await pollRequest()

    loading.value = false

    await router.push('/vote/' + result)
  }

  async function pollRequest() {

    const pollOptions2 = pollOptions.value.map((option: any) => {
      return { label: option };
    });

    const pollData = {
      pollName: pollName.value,
      creatorName: pollAuthor.value,
      pollOptions: pollOptions2
    };

    console.log(JSON.stringify(pollData));

    try {
      const response = await fetch(`${config.apiUrl}/v1/polls/create`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(pollData)
      });

      if (!response.ok) {
        throw new Error('HTTPS error ' + response.status);
      }

      const data = await response.json();

      return data.url;
    } catch (error) {
      console.error('Failed to post data:', error);
    }
  }

</script>

<style scoped>

</style>