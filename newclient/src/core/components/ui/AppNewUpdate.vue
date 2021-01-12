<template>
    <div v-if="display">
        <v-btn v-if="$vuetify.breakpoint.name !== 'xs'" class="new-alert text-capitalize ma-0 caption" flat @click="onReload">
            {{ buttonText }}
            <v-icon class="ml-2 secondary--text fas fa-sync small-global-icon-font"></v-icon>
        </v-btn>
        <v-tooltip v-else right color="white" top content-class="tooltip-border-update">
            <template #activator="{ on }">
                <v-btn class="new-alert new-alert-small ma-0 px-0 caption" flat icon @click="onReload" v-on="on">
                    <v-icon class="secondary--text fas fa-sync small-global-icon-font"></v-icon>
                </v-btn>
            </template>
            <span class="black--text caption text-capitalize"> {{ buttonText }}</span>
        </v-tooltip>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator'

@Component
export default class AppNewUpdate extends Vue {
    /*
    ===================================================================================
      Props:
    ===================================================================================
  */
    @Prop({ type: String, default: '' }) text!: string
    @Prop({ type: Number, default: 0 }) updateCount!: number
    @Prop({ type: Boolean, default: false }) hideCount!: boolean

    /*
    ===================================================================================
      Computed:
    ===================================================================================
  */
    get display(): boolean {
        return this.updateCount > 0
    }

    get buttonText(): string {
        return this.hideCount ? `${this.text}` : `${this.updateCount} ${this.text}`
    }

    /*
    ===================================================================================
      Methods:
    ===================================================================================
  */
    /**
     * Emits reload to parent
     */
    onReload(): void {
        this.$emit('reload')
    }
}
</script>

<style scoped lang="css">
.new-alert {
    height: 30px;
    border: solid 1px #ffb647;
    background-color: rgba(254, 217, 161, 0.25) !important;
}
.new-alert-small {
    max-width: 30px !important;
}
.tooltip-border-update {
    border: solid 1px #ffb647 !important;
    padding: 5px 10px !important;
    opacity: 1 !important;
    box-shadow: none !important;
    background-color: rgba(254, 217, 161, 0.25) !important;
}
.right {
    float: right;
    border: solid 1px #ffb647 !important;
    padding: 5px 10px !important;
    background-color: rgba(254, 217, 161, 0.25) !important;
    margin-left: 5px;
    display: flex;
    z-index: 2;
}
</style>
