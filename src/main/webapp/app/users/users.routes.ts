import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.routes'),
  },
  {
    path: 'client',
    loadChildren: () => import('./client/client.routes'),
  },
  {
    path: 'technician',
    loadChildren: () => import('./technician/technician.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
