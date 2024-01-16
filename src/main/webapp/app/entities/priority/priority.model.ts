import { ITicket } from 'app/entities/ticket/ticket.model';

export interface IPriority {
  id: number;
  name?: string | null;
  description?: string | null;
  colorCode?: string | null;
  ticket?: ITicket | null;
}

export type NewPriority = Omit<IPriority, 'id'> & { id: null };
