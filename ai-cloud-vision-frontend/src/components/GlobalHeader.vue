<script setup lang="ts">
import type { MenuProps } from 'ant-design-vue'
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'

type HeaderMenuItem = {
  key: string
  label: string
  path: string
}

const props = withDefaults(
  defineProps<{
    menuItems: HeaderMenuItem[]
    selectedKeys?: string[]
  }>(),
  {
    menuItems: () => [],
    selectedKeys: () => [],
  },
)

const router = useRouter()
const route = useRoute()

const internalSelectedKeys = computed(() => {
  if (props.selectedKeys.length) return props.selectedKeys
  const active =
    props.menuItems.find((item) => item.path === route.path) ??
    props.menuItems[0]
  return active?.key ? [active.key] : []
})

const menuItems = computed<MenuProps['items']>(() =>
  props.menuItems.map((item) => ({
    key: item.key,
    label: item.label,
  })),
)

const handleMenuClick: MenuProps['onClick'] = ({ key }) => {
  const target = props.menuItems.find((item) => item.key === key)
  if (target && target.path !== route.path) {
    router.push(target.path)
  }
}

const logoSrc =
  'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAABFElEQVR4nO3WsQ2AMAxF0V+6xVnEJMhgmSh8S7TmDNzj5nKRAQAAAAAAAAAA4OaZb5R2wbVuQ9Qx1rQCNZEz7Nd6CM1kTPs13oIzWRM+zXegjNZEz7Nd6CM1kTPs13oILeff7xqACIJpVqvVaq/32bXbPWgAiCaVar1Wqv99m12z1oAIgmlWq9Vqr/fZtds9aACIJpVqvVaq/32bXbPWgAiCaVar1Wqv99m12z1oAIgmlWq9Vqr/fZtds9aACIJpVqvVaq/32bXbPWgAiCaVar1Wqv99m12z1oAIgmlWq9Vqr/fZtds9aACIJpVqvVaq/32bXbPWgAiCaVar1Wqv99m12z1oAIgmlWq9Vql3v7/Y7tYB+hxn3tvX7l2r1QAAAAAAAAAAAABgPwF6EJbJY7FxMQAAAABJRU5ErkJggg=='
</script>

<template>
  <a-layout-header class="global-header">
    <div class="brand">
      <img class="brand__logo" :src="logoSrc" alt="AI 云视 logo" />
      <span class="brand__title">AI 云视项目</span>
    </div>

    <a-menu
      class="nav-menu"
      mode="horizontal"
      :selected-keys="internalSelectedKeys"
      :items="menuItems"
      @click="handleMenuClick"
    />

    <div class="user-entry">
      <a-button type="primary">登录</a-button>
    </div>
  </a-layout-header>
</template>

<style scoped>
.global-header {
  display: flex;
  align-items: center;
  gap: 16px;
  background: #fff;
  padding: 0 24px;
  box-shadow: 0 2px 8px rgba(15, 18, 21, 0.08);
  position: sticky;
  top: 0;
  z-index: 100;
}

.brand {
  display: flex;
  align-items: center;
  gap: 8px;
  min-width: 180px;
}

.brand__logo {
  width: 40px;
  height: 40px;
  object-fit: cover;
  border-radius: 8px;
}

.brand__title {
  font-size: 18px;
  font-weight: 600;
  color: #111;
  white-space: nowrap;
}

.nav-menu {
  flex: 1;
  min-width: 120px;
  border-bottom: none;
}

.user-entry {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  min-width: 120px;
}

@media (max-width: 768px) {
  .global-header {
    flex-wrap: wrap;
    padding: 12px 16px;
  }

  .brand {
    width: 100%;
    justify-content: center;
  }

  .nav-menu {
    width: 100%;
    justify-content: center;
  }

  .user-entry {
    width: 100%;
    justify-content: center;
  }
}
</style>
 