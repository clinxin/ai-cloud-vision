<script setup lang="ts">
import { computed } from 'vue'
import { RouterView, useRoute } from 'vue-router'

import GlobalHeader from '@/components/GlobalHeader.vue'
import GlobalFooter from '@/components/GlobalFooter.vue'

type HeaderMenuItem = {
  key: string
  label: string
  path: string
}

const menuItems: HeaderMenuItem[] = [
  { key: 'home', label: '首页', path: '/' },
  { key: 'about', label: '关于', path: '/about' },
]

const route = useRoute()

const activeMenuKey = computed(() => {
  const active =
    menuItems.find((item) => item.path === route.path) ?? menuItems[0]
  return active?.key ? [active.key] : []
})
</script>

<template>
  <a-layout class="basic-layout">
    <GlobalHeader :menu-items="menuItems" :selected-keys="activeMenuKey" />
    <a-layout-content class="basic-layout__content">
      <RouterView />
    </a-layout-content>
    <GlobalFooter />
  </a-layout>
</template>

<style scoped>
.basic-layout {
  min-height: 100vh;
  background: #f5f6f8;
  display: flex;
  flex-direction: column;
}

.basic-layout :deep(.ant-layout) {
  background: transparent;
}

.basic-layout__content {
  flex: 1;
  padding: 24px;
}

@media (max-width: 768px) {
  .basic-layout__content {
    padding: 16px;
  }
}
</style>
 