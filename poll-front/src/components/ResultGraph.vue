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
          <h1 class="pb-4">{{pollInfo.pollName}}</h1>
          <h2>by {{pollInfo.pollAuthor}}</h2>
          <Pie :data="chartData" :option="optionData"></Pie>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup lang="ts">
  import Chart from 'chart.js/auto'
  import { Pie } from 'vue-chartjs'
  import {ref} from "vue";
  import {useRoute} from "vue-router";
  import {BarElement, CategoryScale, Legend, LinearScale, Title, Tooltip} from "chart.js";
  import {config} from "@/config";

  Chart.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)

  const route = useRoute();
  const pollInfo = ref({pollName: "", pollAuthor: ""});
  const chartData = await formatChartData();
  const optionData = ref({responsive: true})

  async function formatChartData(){
    const poll = await getPollRequest();
    const charData = {
      labels: poll.value.pollOption.map(option => option.label),
      datasets: [
        {
          data: poll.value.pollOption.map(option => option.voteCount)
        }
      ]
    }
    pollInfo.value.pollName = poll.value.pollName;
    pollInfo.value.pollAuthor = poll.value.authorName;
    return ref(charData);
  }

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