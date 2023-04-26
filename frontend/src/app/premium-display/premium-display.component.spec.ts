import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PremiumDisplayComponent } from './premium-display.component';

describe('PremiumDisplayComponent', () => {
  let component: PremiumDisplayComponent;
  let fixture: ComponentFixture<PremiumDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PremiumDisplayComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PremiumDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
