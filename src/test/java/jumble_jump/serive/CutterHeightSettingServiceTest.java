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
    private RiceCakes riceCakes;

    @BeforeEach
    public void setUp() {
        cutter = new Cutter();
        cutterControlUnit = new CutterControlUnit();

        // 떡 높이 리스트: 19, 15, 10, 17
        List<RiceCake> riceCakeList = Arrays.asList(
                new RiceCake(19),
                new RiceCake(15),
                new RiceCake(10),
                new RiceCake(17)
        );

        riceCakes = new RiceCakes(riceCakeList);
    }

    @Test
    public void testSetCutterHeightSuccess() {
        Order order = new Order(riceCakes, 4);

        cutterHeightSettingService = new CutterHeightSettingService(cutter, order, cutterControlUnit);
        cutterHeightSettingService.setCutterHeight();

        assertEquals(16, cutter.getHeight());
    }

    @Test
    public void 나누어_떨어지지_않을_떄_절단기_최대길이_반환() {
        Order order = new Order(riceCakes, 5);

        cutterHeightSettingService = new CutterHeightSettingService(cutter, order, cutterControlUnit);
        cutterHeightSettingService.setCutterHeight();

        assertEquals(15, cutter.getHeight());
    }

}