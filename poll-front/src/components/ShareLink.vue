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
        <h2 class="h2 pa-4">Share the link</h2>
        <v-row class="ma-0">
          <v-text-field
              :value="voteLink"
              variant="outlined"
              density="compact"
          >

          </v-text-field>
          <v-btn
              @click="toClipboard"
              color="blue"
              variant="elevated"
              size="44"
              rounded="lg"
              icon="fas fa-copy"
              class="v-btn--size-default"
          />
        </v-row>
        </v-sheet>
      </v-col>
    </v-row>
  </v-container>
</template>

<script setup lang="ts">
import {useRoute} from "vue-router";
import {config} from "@/config";

const route = useRoute();
const voteLink = `${config.siteUrl}/vote/${route.params.id}`;

function toClipboard(){
  navigator.clipboard.writeText(voteLink);
}
</script>


<style scoped>

</style>