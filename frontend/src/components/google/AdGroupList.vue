<template>
  <el-table :data="data" style="width: 100%">
    <el-table-column prop="id" label="id" width="180" />
    <el-table-column prop="name" label="name" width="180" />
    <el-table-column prop="status" label="status" />
    <el-table-column prop="campaign" label="campaign" />
    <el-table-column prop="cpmBidMicros" label="cpmBidMicros" />
    <el-table-column prop="resourceName" label="resourceName" />
    <el-table-column prop="type" label="type" />
  </el-table>
</template>

<script>
export default {
  data() {
    return {
      data : null,
    }
  },
  mounted() {
    console.log("mounted");
    this.getCampaignList();
  },
  methods : {
    getCampaignList() {
      this.$axios.get('/api/google/ad-group/allAdGroups/6583317631')
        .then(res => {
          console.log(res.data);
          this.data = res.data;
        }).catch(error => {
          console.log(error);
      })
    }
  }
}
</script>
<style lang="scss">
</style>