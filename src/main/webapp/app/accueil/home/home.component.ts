import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import SharedModule from 'app/shared/shared.module';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';
import { ITicket } from 'app/entities/ticket/ticket.model';
import { TicketService } from 'app/entities/ticket/service/ticket.service';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

@Component({
  standalone: true,
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  imports: [SharedModule, RouterModule],
})
export class HomeComponent implements OnInit, OnDestroy {
  recentTickets: ITicket[] = []; // Initialisé à un tableau vide
  account: Account | null = null;
  showSuccessAlert = true;
  private readonly destroy$ = new Subject<void>();

  constructor(
    private accountService: AccountService,
    private ticketService: TicketService,
    private router: Router,
  ) {}
  ngOnInit(): void {
    this.loadRecentTickets();
    this.accountService
      .getAuthenticationState()
      .pipe(takeUntil(this.destroy$))
      .subscribe(account => {
        this.account = account;
        this.showSuccessAlert = !!account; // Set to false if account is null
      });
  }
  logout(): void {
    this.accountService.logout(); // Supposons que cette méthode efface l'état d'authentification
    this.account = null; // Réinitialise l'état du compte
    this.router.navigate(['/login']); // Navigue vers la page de connexion
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  loadRecentTickets() {
    this.ticketService.queryRecent().subscribe(
      (res: HttpResponse<ITicket[]>) => {
        this.recentTickets = res.body || []; // Assurez-vous que recentTickets est toujours un tableau
      },
      (res: HttpErrorResponse) => this.onError(res.message),
    );
  }

  private onError(errorMessage: string): void {
    // Implémentez votre logique de gestion des erreurs ici
    console.error(errorMessage);
  }
}
