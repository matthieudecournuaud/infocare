import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IPriority } from '../priority.model';
import { PriorityService } from '../service/priority.service';

@Component({
  standalone: true,
  templateUrl: './priority-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class PriorityDeleteDialogComponent {
  priority?: IPriority;

  constructor(
    protected priorityService: PriorityService,
    protected activeModal: NgbActiveModal,
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.priorityService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
