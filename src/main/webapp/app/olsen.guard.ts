import { CanActivateFn } from '@angular/router';

export const olsenGuard: CanActivateFn = (route, state) => {
  return true;
};
