import { ITicket } from 'app/entities/ticket/ticket.model';

export interface ICategory {
  id: number;
  name?: string | null;
  description?: string | null;
  icon?: string | null;
  ticket?: ITicket | null;
}

export type NewCategory = Omit<ICategory, 'id'> & { id: null };
