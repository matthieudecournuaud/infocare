import { ITicket } from 'app/entities/ticket/ticket.model';

export interface IStatus {
  id: number;
  name?: string | null;
  statusCode?: string | null;
  description?: string | null;
  colorCode?: string | null;
  nextPossibleStatus?: string | null;
  isFinal?: boolean | null;
  ticket?: ITicket | null;
}

export type NewStatus = Omit<IStatus, 'id'> & { id: null };
