package com.github.dockerjava.netty.exec;

import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.exception.DockerException;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.netty.AbstractNettyDockerClientTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.security.SecureRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Test(groups = "integration")
public class InspectContainerCmdExecTest extends AbstractNettyDockerClientTest {

    public static final Logger LOG = LoggerFactory.getLogger(InspectContainerCmdExecTest.class);

    @BeforeTest
    public void beforeTest() throws Exception {
        super.beforeTest();
    }

    @AfterTest
    public void afterTest() {
        super.afterTest();
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        super.beforeMethod(method);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        super.afterMethod(result);
    }

    @Test()
    public void inspectContainer() throws DockerException {

        String containerName = "generated_" + new SecureRandom().nextInt();

        CreateContainerResponse container = dockerClient.createContainerCmd("busybox").withCmd("top")
                .withName(containerName).exec();
        LOG.info("Created container {}", container.toString());
        assertThat(container.getId(), not(isEmptyString()));

        InspectContainerResponse containerInfo = dockerClient.inspectContainerCmd(container.getId()).exec();
        assertEquals(containerInfo.getId(), container.getId());

    }

    @Test
    public void inspectNonExistingContainer() throws DockerException {

        try {
            InspectContainerResponse resp = dockerClient.inspectContainerCmd("a59e89574f38").exec();
            System.out.println(resp);
            fail("expected NotFoundException");
        } catch (NotFoundException e) {
        }
    }

    @Test
    public void inspectContainerRestartCount() throws DockerException {

        CreateContainerResponse container = dockerClient.createContainerCmd("busybox")
                .withCmd("env").exec();

        LOG.info("Created container {}", container.toString());

        assertThat(container.getId(), not(isEmptyString()));

        InspectContainerResponse inspectContainerResponse = dockerClient.inspectContainerCmd(container.getId()).exec();

        assertThat(inspectContainerResponse.getRestartCount(), equalTo(0));
    }

    @Test
    public void inspectContainerNetworkSettings() throws DockerException {

        CreateContainerResponse container = dockerClient.createContainerCmd("busybox")
                .withCmd("env").exec();

        LOG.info("Created container {}", container.toString());

        assertThat(container.getId(), not(isEmptyString()));

        InspectContainerResponse inspectContainerResponse = dockerClient.inspectContainerCmd(container.getId()).exec();

        assertFalse(inspectContainerResponse.getNetworkSettings().getHairpinMode());
    }
}
