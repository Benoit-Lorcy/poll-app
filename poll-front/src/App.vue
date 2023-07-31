<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <v-app>
    <v-app-bar>
      <v-container class="fill-height d-flex">
        <img alt="Vue logo" class="logo" src="../public/Paull.svg" height="30" />
        <v-spacer></v-spacer>

        <RouterLink to="/">
          <v-btn
            color="blue"
            variant="elevated"
          >New Poll</v-btn>
        </RouterLink>

        <!--<v-btn><RouterLink to="/about">About</RouterLink></v-btn>-->
      </v-container>
    </v-app-bar>
    <v-main>
      <RouterView />
    </v-main>
  </v-app>
</template>

<style scoped>

</style>
