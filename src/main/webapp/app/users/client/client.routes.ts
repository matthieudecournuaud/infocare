import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'materiels',
    loadComponent: () => import('./materiels/materiels.component'),
  },
  {
    path: 'mes-tickets',
    loadComponent: () => import('./mes-tickets/mes-tickets.component'),
  },
  {
    path: 'profil',
    loadComponent: () => import('./profil/profil.component'),
  },
];

export default routes;
