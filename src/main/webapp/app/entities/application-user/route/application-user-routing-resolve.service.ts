import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of, EMPTY, Observable } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IApplicationUser } from '../application-user.model';
import { ApplicationUserService } from '../service/application-user.service';

export const applicationUserResolve = (route: ActivatedRouteSnapshot): Observable<null | IApplicationUser> => {
  const id = route.params['id'];
  if (id) {
    return inject(ApplicationUserService)
      .find(id)
      .pipe(
        mergeMap((applicationUser: HttpResponse<IApplicationUser>) => {
          if (applicationUser.body) {
            return of(applicationUser.body);
          } else {
            inject(Router).navigate(['404']);
            return EMPTY;
          }
        }),
      );
  }
  return of(null);
};

export default applicationUserResolve;
