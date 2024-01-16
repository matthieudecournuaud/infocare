import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'ticket',
    data: { pageTitle: 'infocareApp.ticket.home.title' },
    loadChildren: () => import('./ticket/ticket.routes'),
  },
  {
    path: 'application-user',
    data: { pageTitle: 'infocareApp.applicationUser.home.title' },
    loadChildren: () => import('./application-user/application-user.routes'),
  },
  {
    path: 'category',
    data: { pageTitle: 'infocareApp.category.home.title' },
    loadChildren: () => import('./category/category.routes'),
  },
  {
    path: 'status',
    data: { pageTitle: 'infocareApp.status.home.title' },
    loadChildren: () => import('./status/status.routes'),
  },
  {
    path: 'priority',
    data: { pageTitle: 'infocareApp.priority.home.title' },
    loadChildren: () => import('./priority/priority.routes'),
  },
  {
    path: 'material',
    data: { pageTitle: 'infocareApp.material.home.title' },
    loadChildren: () => import('./material/material.routes'),
  },
  {
    path: 'company',
    data: { pageTitle: 'infocareApp.company.home.title' },
    loadChildren: () => import('./company/company.routes'),
  },
  {
    path: 'comment',
    data: { pageTitle: 'infocareApp.comment.home.title' },
    loadChildren: () => import('./comment/comment.routes'),
  },
  {
    path: 'intervention',
    data: { pageTitle: 'infocareApp.intervention.home.title' },
    loadChildren: () => import('./intervention/intervention.routes'),
  },
  {
    path: 'procedure',
    data: { pageTitle: 'infocareApp.procedure.home.title' },
    loadChildren: () => import('./procedure/procedure.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
