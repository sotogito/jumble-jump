package jumble_jump.serive;

import jumble_jump.domain.Cutter;
import jumble_jump.domain.CutterControlUnit;
import jumble_jump.domain.RiceCake;
import jumble_jump.repository.Order;
import jumble_jump.repository.RiceCakes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CutterHeightSettingServiceTest {
    private CutterHeightSettingService cutterHeightSettingService;
    private Cutter cutter;
    private CutterControlUnit cutterControlUnit;

    @BeforeEach
    public void setUp() {
        cutter = new Cutter();
        cutterControlUnit = new CutterControlUnit();

        // 떡 높이 리스트: 19, 15, 10, 17
        List<RiceCake> riceCakes = Arrays.asList(
                new RiceCake(19),
                new RiceCake(15),
                new RiceCake(10),
                new RiceCake(17)
        );

        RiceCakes riceCakesRepository = new RiceCakes(riceCakes);

        // 떡의 목표 길이는 6으로 설정
        Order order = new Order(riceCakesRepository, 4);

        cutterHeightSettingService = new CutterHeightSettingService(cutter, order, cutterControlUnit);
    }

    @Test
    public void testSetCutterHeightSuccess() {
        // 절단기 높이 설정
        cutterHeightSettingService.setCutterHeight();

        // 최종적으로 설정된 절단기 높이는 15이어야 한다 (예상치)
        assertEquals(16, cutter.getHeight());
    }

}